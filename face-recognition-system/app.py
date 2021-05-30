from flask import Flask, request, jsonify, render_template, Response
from flask_cors import CORS
import os
import cv2
import numpy as np
import mysql.connector
import datetime
from common_util import hms_to_seconds
from camera_detection import VideoCamera

# Get the relative path to this file (we will use it later)
FILE_PATH = os.path.dirname(os.path.dirname(os.path.realpath(__file__)))

# * ---------- Create App --------- *
app = Flask(__name__, template_folder='template')
CORS(app, support_credentials=True)

# * ---------- DATABASE CONFIG --------- *
DATABASE_USER = 'root'
DATABASE_PASSWORD = '123456'
DATABASE_HOST = 'localhost'
DATABASE_PORT = '3306'
DATABASE_NAME = 'timekeeping-manager'


def database_connection():
    return mysql.connector.connect(user=DATABASE_USER,
                                   password=DATABASE_PASSWORD,
                                   host=DATABASE_HOST,
                                   port=DATABASE_PORT,
                                   database=DATABASE_NAME)


# * --------------------  ROUTES ------------------- *
# * ---------- Get data from the face recognition ---------- *
@app.route('/receive_data', methods=['POST'])
def get_receive_data():
    if request.method == 'POST':
        json_data = request.get_json()

        # Check if the user is already in the DB
        connection = database_connection()
        try:
            # Connect to the DB
            cursor = connection.cursor()

            # Get employee_id by employee_code from json data
            employee_id_query = \
                f"SELECT employee_id FROM employee WHERE 1=1 AND employee_code = '{json_data['employeeCode']}'"
            cursor.execute(employee_id_query)
            employee_id = cursor.fetchone()

            cursor = connection.cursor()
            # Query to check if the user as been saw by the camera today
            user_saw_today_sql_query = \
                f"SELECT arrival_time, departure_time FROM timekeeping WHERE 1=1 AND " \
                f"DATE(date_timekeeping) = '{json_data['date']}' AND employee_id = {employee_id[0]}"
            cursor.execute(user_saw_today_sql_query)
            result = cursor.fetchall()
            connection.commit()
            # If use is already in the DB for today:
            if result:
                print('User OUT')
                time_in = result[0][0]
                time_out = result[0][1]
                time_now = datetime.datetime.now()
                is_continue = True
                if time_out is None:
                    minus_time = datetime.timedelta(
                        seconds=hms_to_seconds(time_now.hour, time_now.minute, time_now.second)) - time_in
                    if minus_time < datetime.timedelta(minutes=5):
                        is_continue = False
                else:
                    minus_time = datetime.timedelta(
                        seconds=hms_to_seconds(time_now.hour, time_now.minute, time_now.second)) - time_out
                    if minus_time < datetime.timedelta(minutes=5):
                        is_continue = False

                if is_continue is True:
                    image_path = f"{FILE_PATH}/assets/img/{json_data['date']}/{json_data['employeeCode']}/departure.jpg"

                    # Save image
                    os.makedirs(f"{FILE_PATH}/assets/img/{json_data['date']}/{json_data['employeeCode']}",
                                exist_ok=True)
                    cv2.imwrite(image_path, np.array(json_data['picture_array']))
                    json_data['picture_path'] = image_path

                    # Update timekeeping in the DB
                    update_timekeeping_query = \
                        f"UPDATE timekeeping SET departure_time = '{json_data['hour']}', " \
                        f"departure_picture = '{json_data['picture_path']}', left_early = {json_data['left_early']} " \
                        f"WHERE employee_id = {employee_id[0]} " \
                        f"AND DATE(date_timekeeping) = '{json_data['date']}'"
                    cursor.execute(update_timekeeping_query)
                else:
                    print('Ban ghi moi chua qua 5 phut')
            else:
                print("User IN")
                # Save image
                image_path = \
                    f"{FILE_PATH}/assets/img/history/{json_data['date']}/{json_data['employeeCode']}/arrival.jpg"
                os.makedirs(f"{FILE_PATH}/assets/img/history/{json_data['date']}/{json_data['employeeCode']}",
                            exist_ok=True)
                cv2.imwrite(image_path, np.array(json_data['picture_array']))
                json_data['picture_path'] = image_path

                # Create a new row for the user today:
                insert_user_query = f"INSERT INTO timekeeping " \
                                    f"(employee_id, date_timekeeping, arrival_time, arrival_picture, is_late) VALUES " \
                                    f"({employee_id[0]}, " \
                                    f"'{json_data['date']}', " \
                                    f"'{json_data['hour']}', " \
                                    f"'{json_data['picture_path']}', " \
                                    f"{json_data['is_late']})"
                cursor.execute(insert_user_query)

        except (Exception, mysql.connector.DatabaseError) as error:
            print("ERROR DB: ", error)
        finally:
            connection.commit()

            # closing database connection.
            if connection:
                cursor.close()
                connection.close()
                print("MySQL connection is closed")

            # Return user's data to the front
        return jsonify(json_data)


# def gen(video_capture):
#     while True:
#         success, frame = video_capture.read()
#         if not success:
#             break
#         else:
#             cv2.setNumThreads(8)
#             frame = cv2.resize(frame, (1280, 720))
#             ret, buffer = cv2.imencode('.jpg', frame)
#             frame_bytes = buffer.tobytes()
#             yield (b'--frame\r\n'
#                    b'Content-Type: image/jpeg\r\n\r\n' + frame_bytes + b'\r\n')


def gen(camera):
    while True:
        frame = camera.get_frame()
        yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')


@app.route('/video_feed')
def video_feed():
    return Response(gen(VideoCamera()), mimetype='multipart/x-mixed-replace; boundary=frame')


@app.route('/')
def home():
    return render_template('index.html')


if __name__ == '__main__':
    app.run(host='127.0.0.1', port=5000, debug=True)
