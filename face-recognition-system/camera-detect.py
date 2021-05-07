import cv2
import pickle
cap = cv2.VideoCapture(0)
pickle.load(open('model_face_train.sav', 'rb'))
while True:
    ret, frame = cap.read()
    cv2.imshow('Face detect', frame)

    k = cv2.waitKey(30) & 0xff
    if k == 27:
        break
cap.release()
cv2.destroyAllWindows()
