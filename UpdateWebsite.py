from selenium import webdriver
from pynput.keyboard import Key, Controller
import sys

keyboard = Controller()

weight = sys.argv[1]
username = 'login'
password = 'details'

url = 'https://www.x4iiiis.com/admin'

driver = webdriver.Chrome(r"C:\Users\ryan_\Downloads\chromedriver_win32\chromedriver.exe")

driver.get(url)

driver.find_element_by_id('id_username').send_keys(username)
driver.find_element_by_id('id_password').send_keys(password)

keyboard.press(Key.enter)
keyboard.release(Key.enter)

url += '/bodybuilding/weighin/add'

driver.get(url)

keyboard.type(weight)

driver.find_element_by_link_text('Today').click()
driver.find_element_by_link_text('Now').click()
driver.find_element_by_class_name('default').click()
