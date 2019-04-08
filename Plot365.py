from matplotlib import pyplot as plt
from matplotlib import style
import numpy as np 

import sys,os

style.use('ggplot')

weights = np.loadtxt('WeighIns.csv', unpack = True, delimiter = '\n')
minX = [364, weights.size-1]

#Plot 365 days
plt.plot(weights[0:weights.size], color='gold')

plt.title('Last 365 Days')
plt.xlim([0,min(minX)])
plt.ylim([min(weights[0:365]), max(weights[0:365])])
plt.draw()
plt.savefig('Graph365.png', transparent=True, bbox_inches="tight", dpi=1000)
print("Graph365.png saved")
