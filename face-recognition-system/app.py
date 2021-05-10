from flask import Flask, request, jsonify
from flask_cors import CORS
import os
import cv2
import numpy as np
import mysql.connector

# Get the relative path to this file (we will use it later)
FILE_PATH = os.path.dirname(os.path.dirname(os.path.realpath(__file__)))

# * ---------- Create App --------- *
app = Flask(__name__)
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
                f"SELECT * FROM timekeeping WHERE 1=1 AND DATE(date_timekeeping) = '{json_data['date']}' " \
                f"AND employee_id = {employee_id[0]}"
            cursor.execute(user_saw_today_sql_query)
            result = cursor.fetchall()
            print(result)
            connection.commit()
            # If use is already in the DB for today:
            if result:
                print('User OUT')
                image_path = f"{FILE_PATH}/assets/img/{json_data['date']}/{json_data['employeeCode']}/departure.jpg"

                # Save image
                os.makedirs(f"{FILE_PATH}/assets/img/{json_data['date']}/{json_data['employeeCode']}", exist_ok=True)
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


if __name__ == '__main__':
    app.run(host='127.0.0.1', port=5000, debug=True)
