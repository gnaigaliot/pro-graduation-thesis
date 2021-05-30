import os
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
        ret, frame = self.video.read()
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

        while True:
            # Process every frame only one time
            if process_this_frame:
                # Find all the faces and face encodings in the current frame of video
                face_locations = face_recognition.face_locations(frame)
                face_encodings = face_recognition.face_encodings(frame,
                                                                 face_locations)  # xoay, lat, zoom anh 100 lan de tang do chinh xac

                # Initialize an array for the name of the detected users
                face_name = []

                # * ---------- Initial JSON to EXPORT --------- *
                json_to_export = {}

                for face_encoding in face_encodings:
                    # Check if the face is match for the known face
                    matches = face_recognition.compare_faces(known_face_encodings, face_encoding)
                    name = 'Unknown'

                    # # If a match was found in known_face_encodings, just use the first one.
                    # if True in matches:
                    #     first_match_index = matches.index(True)
                    #     name = known_face_names[first_match_index]

                    # Or instead, use the known face with the smallest distance to the new face
                    face_distances = face_recognition.face_distance(known_face_encodings, face_encoding)
                    best_match_index = np.argmin(face_distances)
                    if matches[best_match_index]:
                        name = known_face_names[best_match_index]

                        # * ---------- SAVE data to send to the API -------- *
                        json_to_export['employeeCode'] = name
                        json_to_export['hour'] = \
                            f'{time.localtime().tm_hour}:{time.localtime().tm_min}:{time.localtime().tm_sec}'
                        json_to_export['date'] = \
                            f'{time.localtime().tm_year}-{time.localtime().tm_mon}-{time.localtime().tm_mday}'
                        json_to_export['picture_array'] = frame.tolist()

                        # Check time arrive is late or departure is soon
                        if (datetime.timedelta(seconds=hms_to_seconds(time.localtime().tm_hour,
                                                                      time.localtime().tm_min,
                                                                      time.localtime().tm_sec)) -
                            datetime.timedelta(seconds=hms_to_seconds(8, 30, 0))) >= datetime.timedelta(minutes=1):
                            json_to_export['is_late'] = 1
                        else:
                            json_to_export['is_late'] = 0

                        if (datetime.timedelta(seconds=hms_to_seconds(time.localtime().tm_hour,
                                                                      time.localtime().tm_min,
                                                                      time.localtime().tm_sec)) -
                            datetime.timedelta(seconds=hms_to_seconds(17, 30, 0))) >= datetime.timedelta(minutes=1):
                            json_to_export['left_early'] = 1
                        else:
                            json_to_export['left_early'] = 0
                        # * ---------- SEND data to API --------- *
                        r = requests.post(url='http://127.0.0.1:5000/receive_data', json=json_to_export)
                        print("Status: ", r.status_code)

                    face_names.append(name)

            process_this_frame = not process_this_frame
            ret, jpeg = cv2.imencode('.jpg', frame)

            return jpeg.tobytes()