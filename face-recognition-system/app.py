from flask import *
from flask_socketio import SocketIO, emit
from bson.objectid import ObjectId
import json
import cv2
import shutil
import os
import train_face
import time
import numpy as np
import datetime
import torch
from facenet_pytorch import MTCNN
import random
import pickle
import string

app = Flask(__name__)
app.config['SECRET_KEY'] = 'secret!'
socketio = SocketIO(app, cors_allowed_origins="*")
camera_one = cv2.VideoCapture(0)
device = torch.device('cuda:0' if torch.cuda.is_available() else 'cpu')
print('Running on device: {}'.format(device))
mtcnn = MTCNN(keep_all=True, device=device)
MODEL = True
model_predict = pickle.load(open("model_face_train.sav", "rb"))
value_face = []


def gen_frames():
    while True:
        # Capture frame-by-frame
        success, frame = camera_one.read()  # read the camera frame
        if not success:
            break
        else:
            cv2.setNumThreads(8)
            frame = cv2.resize(frame, (1280, 720))
            ret, buffer = cv2.imencode('.jpg', frame)
            frame_bytes = buffer.tobytes()
            yield (b'--frame\r\n'
                   b'Content-Type: image/jpeg\r\n\r\n' + frame_bytes + b'\r\n')


@socketio.on("total_face_detection")
def send_total_face_detection():
    emit("result_face_detection", {"total_employee": 1, "total_guest": 1})


@socketio.on("start_detection")
def detection_model():
    set_fps = 0
    while MODEL:
        ret, frame = camera_one.read()
        if not ret:
            break
        else:
            if set_fps == 5 or set_fps == 10 or set_fps == 15 or set_fps == 20 or set_fps == 25:
                detection_face(frame)
                set_fps += 1
                if set_fps > 25:
                    set_fps = 1
            else:
                set_fps += 1


def detection_face(frame):
    list_predict_name = []
    boxes, _ = mtcnn.detect(frame)
    if boxes is not None:
        for box in boxes:
            if box[0] < 0 or box[1] < 0 or box[2] < 0 or box[3] < 0:
                continue
            if abs(int(box[1]) - int(box[3])) < 40 or abs(int(box[0]) - int(box[2])) < 40:
                continue
            image_face = cv2.resize(frame[int(box[1]) - 3:int(box[3] + 3), int(box[0] - 3):int(box[2] + 3)],
                                    (128, 128))
            faces, acc = mtcnn.detect(image_face)
            if faces is None:
                continue
            if acc[0] * 100 < 95:
                continue
            # cv2.imwrite(os.path.join("static/face", id_generator(6) + ".jpg"), image_face)
            list_predict = model_predict.predict_proba(np.array(image_face).flatten().reshape(1, -1)).flatten()
            best_predict = np.argmax(list_predict, axis=0)
            value_predict = -1
            if int(list_predict[best_predict] * 100) > 75:
                value_predict = best_predict
            if value_predict == -1:
                list_predict_name.append(-1)
            else:
                list_predict_name.append(value_predict)

            cv2.imwrite("detection.jpg", image_face)
            if value_predict == -1:
                with open("detection.jpg", 'rb') as file_image:
                    image_data = file_image.read()
                emit("face_detection_check_in", {"name": "Nguoi La",
                                                 "time": datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'),
                                                 "image_data": image_data})


@socketio.on("path_from_id")
def send_total_face_detection(path):
    list_file = []
    for file in os.listdir(path["path"]):
        list_file.append(os.path.join(path["path"], file))
    emit("result_path_file", {"path_file": list_file})


@app.route('/render_camera_one')
def render_camera_one():
    return Response(gen_frames(), mimetype='multipart/x-mixed-replace; boundary=frame')


@app.route("/train-face")
def train_face():
    train_face.train_main()
    render_template("index.html")


@app.route('/')
def home():
    for root, dirs, files in os.walk("../train-model-image", topdown=False):
        for name in dirs:
            value_face.append(name)
    return render_template("index.html")


if __name__ == '__main__':
    socketio.run(app.run())
