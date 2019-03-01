import sys
import os
import io
from sympy import *

sys.stdout = io.TextIOWrapper(sys.stdout.buffer,encoding='utf8') #
sys.path.append("C:/Users/User/Google 云端硬盘/GR/")


fPath = 'C:/Users/User/IdeaProjects/GR/tmp'
from gr import *


def write_obj(obj):
    with open(fPath, 'w', encoding='utf8') as f:
        f.write(str(obj))

def read_obj():
    try:
        with open(fPath, 'r', encoding='utf8') as f:
            for line in f:
                print(line)
    except Exception as e:
        print(e)

def test():
    R = sympy.Symbol('R')
    psi = UC.psi
    theta = UC.theta
    phi = UC.phi
    g = GeometricTensor(psi, theta, phi)
    g[0, 0] = R**2 * sympy.cos(theta)**2
    g[1, 1] = R**2
    g[2, 2] = R**2 * sympy.sin(theta)**2
    r = ChristoffelSymbol(g)
    # write_obj(r)
    print(str(r))


# insert
a=sympy.Symbol('a')
b=sympy.Symbol('b')
c=sympy.Symbol('c')
d=sympy.Symbol('d')
g=GeometricTensor(a, b, c, d)
g[0,0]=a
g[0,1]=0
g[0,2]=0
g[0,3]=0
g[1,0]=0
g[1,1]=132
g[1,2]=0
g[1,3]=0
g[2,0]=0
g[2,1]=0
g[2,2]=3123
g[2,3]=0
g[3,0]=0
g[3,1]=0
g[3,2]=0
g[3,3]=d**2 * sin(a)
ChristoffelSymbol_instance = ChristoffelSymbol(g)
print(ChristoffelSymbol_instance)
RiemannTensor_instance = RiemannTensor(g)
print(RiemannTensor_instance)