from matplotlib import pyplot as plt
from matplotlib import style
import numpy as np 

import sys,os

style.use('ggplot')

weights = np.loadtxt('WeighIns.csv', unpack = True, delimiter = '\n')


#Plot 7 days
plt.plot(weights[0:7])

plt.title('Last 7 Days')
plt.xlim([0,6])
plt.ylim([min(weights[0:7]), max(weights[0:7])])

plt.draw()
plt.savefig('Graph7.png', transparent=True, bbox_inches="tight", dpi=1000)
print("Graph7.png saved")

#Plot 28 days
plt.plot(weights[0:28])

plt.title('Last 28 Days')
plt.xlim([0,27])
plt.ylim([min(weights[0:28]), max(weights[0:28])])

plt.draw()
plt.savefig('Graph28.png', transparent=True, bbox_inches="tight", dpi=1000)
print("Graph28.png saved")

#Plot 365 days
plt.plot(weights[0:365])

plt.title('Last 365 Days')
plt.xlim([0,364])
plt.ylim([min(weights[0:365]), max(weights[0:365])])

plt.draw()
plt.savefig('Graph365.png', transparent=True, bbox_inches="tight", dpi=1000)
print("Graph365.png saved")
