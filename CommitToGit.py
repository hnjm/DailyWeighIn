import time, datetime
from pynput.keyboard import Key, Controller

keyboard = Controller()

#Press Windows Key
keyboard.press(Key.cmd)
keyboard.release(Key.cmd)

#Search for Command Prompt
time.sleep(2)
keyboard.type("cmd")

#Open CMD
time.sleep(2)
keyboard.press(Key.enter)
keyboard.release(Key.enter)

#Change directory to the weigh-ins folder
time.sleep(2)
keyboard.type(r"cd C:\Users\ryan_\Desktop\DailyWeighIn\DailyWeighIn")
keyboard.press(Key.enter)
keyboard.release(Key.enter)

#Add all changed files to be committed
time.sleep(2)
keyboard.type("git add .")
keyboard.press(Key.enter)
keyboard.release(Key.enter)

#Commit to git with today's date and a loving description
time.sleep(2)
today = datetime.datetime.now()
keyboard.type('git commit -m "')
keyboard.type(today.strftime('%A %d %B %Y"'))
keyboard.type(' -m "Sent with love from Python!"')
keyboard.press(Key.enter)
keyboard.release(Key.enter)

#Push changes to GitHub     https://github.com/x4iiiis/DailyWeighIn
time.sleep(2)
keyboard.type('git push origin master')
keyboard.press(Key.enter)
keyboard.release(Key.enter)

#Close CMD
time.sleep(2)
keyboard.type('exit')
keyboard.press(Key.enter)
keyboard.release(Key.enter)
