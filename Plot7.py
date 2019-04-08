from matplotlib import pyplot as plt
from matplotlib import style
import numpy as np 

import sys,os

style.use('ggplot')

weights = np.loadtxt('WeighIns.csv', unpack = True, delimiter = '\n')
minX = [0, weights.size-7]

#Plot 7 days
plt.plot(weights[0:weights.size], color='limegreen')

plt.title('Last 7 Days')
plt.xlim([max(minX),weights.size-1])
plt.ylim([min(weights[weights.size-7:weights.size]), max(weights[weights.size-7:weights.size])])

plt.draw()
plt.savefig('Graph7.png', transparent=True, bbox_inches="tight", dpi=1000)
print("Graph7.png saved")
