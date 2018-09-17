from matplotlib import pyplot as plt
from matplotlib import style
import numpy as np 

import sys,os

style.use('ggplot')

weights = np.loadtxt('WeighIns.csv', unpack = True, delimiter = '\n')
minX = [0, weights.size-28]

#Plot 28 days
plt.plot(weights[weights.size-365:weights.size], color='magenta')

plt.title('Last 28 Days')
plt.xlim([max(minX),weights.size-1])
plt.ylim([min(weights[weights.size-28:weights.size]), max(weights[weights.size-28:weights.size])])
plt.draw()
plt.savefig('Graph28.png', transparent=True, bbox_inches="tight", dpi=1000)
print("Graph28.png saved")
