import numpy as np
import matplotlib.pyplot as plt


def processData(filename):
    with open(filename, 'r') as f:
        arr = f.read().split("\n")[:-1]
        # print(arr)
    new_arr_x = []
    new_arr_y = []
    for i in range(len(arr)):
        new_arr_x.append(int(arr[i].split(" ")[0]))
        new_arr_y.append(int(arr[i].split(" ")[1]))

    return [new_arr_x,new_arr_y]


def plotData(arr, name):
    plt.clf()
    print(arr)
    plt.plot(arr[1],arr[0], '-p',color='mediumpurple',markerfacecolor="crimson")
    plt.xlabel('disk blocks')
    plt.ylabel('time')
    plt.title(name)
    plt.savefig(f'imgs/{name}.png')
    # plt.show()
    



plotData(processData("FCFS.txt"), "FCFS")
plotData(processData("SSTF.txt"), "SSTF")
plotData(processData("SCAN.txt"), "SCAN")
plotData(processData("C-SCAN.txt"), "C-SCAN")
plotData(processData("EDF.txt"), "EDF")
plotData(processData("FD-SCAN.txt"), "FD-SCAN")





