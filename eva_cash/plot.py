import matplotlib.pyplot as plt

with open('withCache.txt', 'r') as file:
    withCacheTime = tuple(map(lambda x: int(x[:-1]), file.readlines())) 

with open('withoutCache.txt', 'r') as file:
    withoutCacheTime = tuple(map(lambda x: int(x[:-1]), file.readlines())) 

fig = plt.figure(1)

plt.subplot(2, 1, 1)
plt.ylabel("With cache")
plt.plot(withCacheTime)

plt.subplot(2, 1, 2)
plt.ylabel("Without cache")
plt.plot(withoutCacheTime)

plt.show()
