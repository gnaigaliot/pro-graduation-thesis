import cv2
import face_recognition
import numpy as np
import time
import requests
import os
from common_util import hms_to_seconds
import datetime


class VideoCamera(object):
    def __init__(self):
        self.video = cv2.VideoCapture(0)

    def __del__(self):
        self.video.release()

    def get_frame(self):
        success, frame = self.video.read()
        known_face_encodings = []
        known_face_names = []
        known_face_filenames = []
        for (dirpath, dirnames, filenames) in os.walk('../assets/img/users/'):
            known_face_filenames.extend(filenames)
            break

        for filename in known_face_filenames:
            face = face_recognition.load_image_file('../assets/img/users/' + filename)
            split_tup = os.path.splitext(filename)
            if split_tup[1] == '.jpeg':
                known_face_names.append(filename[:-5])
            else:
                known_face_names.append(filename[:-4])
            known_face_encodings.append(face_recognition.face_encodings(face)[0])

        face_locations = []
        face_encodings = []
        face_names = []
        process_this_frame = True

        success, jpeg = cv2.imencode('.jpg', frame)
        return jpeg.tobytes()
