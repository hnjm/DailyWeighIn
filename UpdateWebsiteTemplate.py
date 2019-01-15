from selenium import webdriver
from pynput.keyboard import Key, Controller
import sys

keyboard = Controller()

#Previous script (CommitToGit.py) passes the weight variable
#it receieved from the Java application on to this script
weight = sys.argv[1]

#Login details for x4iiiis.com/admin
username = 'login'
password = 'details'

url = 'https://www.x4iiiis.com/admin'

#Google Chrome webdriver location in downloads folder
driver = webdriver.Chrome(r"C:\Users\ryan_\Downloads\chromedriver_win32\chromedriver.exe")

#Open Google Chrome and navigate to x4iiiis.com/admin
driver.get(url)

#Login to x4iiiis.com/admin
driver.find_element_by_id('id_username').send_keys(username)
driver.find_element_by_id('id_password').send_keys(password)
keyboard.press(Key.enter)
keyboard.release(Key.enter)

#Update the url and navigate to x4iiiis.com/admin/bodybuilding/weighin/add
url += '/bodybuilding/weighin/add'
driver.get(url)

#Enter the weight data passed on from Java via CommitToGit.py
keyboard.type(weight)

#Tell the database what date and time this data is relevant to, and submit
driver.find_element_by_link_text('Today').click()
driver.find_element_by_link_text('Now').click()
driver.find_element_by_class_name('default').click()
