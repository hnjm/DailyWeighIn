from matplotlib import pyplot as plt
from matplotlib import style
import numpy as np 

import sys,os

style.use('ggplot')

weights = np.loadtxt('WeighIns.csv', unpack = True, delimiter = '\n')

plt.plot(weights)

plt.title('Last 7 Days')
plt.ylabel('Weight\n(kg)')
plt.xlabel('Time\n(Days)')

plt.draw()
plt.savefig('Graph7.png', transparent=True, bbox_inches="tight", dpi=100)
