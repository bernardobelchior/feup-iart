import sys 

if (len(sys.argv) < 4):
    print "Usage: <rate-lower-bound> <rate-upper-bound> <epochs> <hiddenLayers>"
    sys.exit()

lowerbound = sys.argv[1]
lowerbound = int(float(lowerbound)*100)
upperbound = sys.argv[2]
upperbound = int(float(upperbound)*100) + 5
epochs = sys.argv[3]
hiddenLayers = sys.argv[4]

for i in range(lowerbound,upperbound,5):
    rate = i * 0.01
    for j in range(1,10):
        momentum = j * 0.1
        print "-L " + str(rate) + " -M " + str(momentum) + " -N " + str(epochs) + " -H " + str(hiddenLayers)

