import numpy as np
from sklearn.svm import SVC
import cv2
import os
import pickle
from sklearn.model_selection import train_test_split


def train_main():
    DIR_FOLDER_TRAIN = '../train-model-image'
    MODEL_NAME = 'model_face_train.sav'
    # load data
    list_employee = []
    for data_folder in os.walk(DIR_FOLDER_TRAIN, topdown=False):
        if len(data_folder[0].split('\\')) > 1 and data_folder[0].split('\\')[-1] != "Unknown":
            list_employee.append(data_folder[0].split('\\')[-1])
    data_train = []
    data_label = []
    print('----- Starting load data from image train folder -----')
    for index, employee in enumerate(list_employee):
        print('--- Start get data from employee %s ---' % employee)
        for file_image in os.listdir(os.path.join(DIR_FOLDER_TRAIN, employee)):
            if file_image.split('.')[-1] != 'png':
                continue
            img = cv2.imread(os.path.join(os.path.join(DIR_FOLDER_TRAIN, employee), file_image))
            print(img)
            data_train.append(np.array(img).flatten())
            data_label.append(int(index))
        print('--- Load data complete from employee %s ---' % employee)
    print('----- End load data from image train folder -----')
    # training
    model = SVC(kernel="linear", probability=True)
    value_train, value_test, label_train, label_test = train_test_split(data_train, np.array(data_label),
                                                                        test_size=0.1, random_state=42)
    model.fit(value_train,
              label_train)
    pickle.dump(model, open(MODEL_NAME, 'wb'))
