import subprocess, time, datetime
from pynput.keyboard import Key, Controller

keyboard = Controller()

keyboard.press(Key.cmd)
keyboard.release(Key.cmd)

time.sleep(2)
keyboard.type("cmd")

time.sleep(2)
keyboard.press(Key.enter)
keyboard.release(Key.enter)

time.sleep(2)
keyboard.type(r"cd C:\Users\ryan_\Desktop\DailyWeighIn\DailyWeighIn")
keyboard.press(Key.enter)
keyboard.release(Key.enter)

time.sleep(2)
keyboard.type("git add .")
keyboard.press(Key.enter)
keyboard.release(Key.enter)

#Commit to Git
time.sleep(2)
today = datetime.datetime.now()
keyboard.type('git commit -m "')
keyboard.type(today.strftime('%A %d %B %Y"'))
keyboard.type(' -m "Sent with love from Python!"')
keyboard.press(Key.enter)
keyboard.release(Key.enter)

time.sleep(2)
keyboard.type('git push origin master')
keyboard.press(Key.enter)
keyboard.release(Key.enter)
