#!/usr/bin/python3

import numpy as np, array
import matplotlib.pyplot as plt


def readFubFile(name):
	
	file = open(name, "r")
	file = file.readlines()
	# print(file)
	value = []
	time = []
	for val in file:
		val = val.split(" ")
		value.append(val[0])
		time.append(val[1])
	
	return dict(data = value, time = time)


def plotFib(data):

	plt.figure(1)
	plt.plot(np.array(data[0]["time"]), np.array(data[0]["data"]))
	plt.title ("iter_fib_50")
	plt.xlabel ("time")
	plt.ylabel ("data")
	plt.grid()

	plt.figure(2)
	plt.plot(np.array(data[1]["time"]), np.array(data[1]["data"]))
	plt.title ("iter_fib_100")
	plt.xlabel ("time")
	plt.ylabel ("data")
	plt.grid()

	plt.figure(3)
	plt.plot(np.array(data[2]["time"]), np.array(data[2]["data"]))
	plt.title ("recur_fib")
	plt.xlabel ("time")
	plt.ylabel ("data")
	plt.grid()

	plt.show()


if __name__ == "__main__":

	test1 = readFubFile("iter_fib_50.txt")
	test2 = readFubFile("iter_fib_100.txt")
	test3 = readFubFile("recur_fib.txt")

	plotFib([test1, test2, test3])

