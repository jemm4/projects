import math
import sys

EPSILON = sys.float_info.epsilon

'''
Given two points, p1 and p2,
an x coordinate, x,
and y coordinates y3 and y4,
compute and return the (x,y) coordinates
of the y intercept of the line segment p1->p2
with the line segment (x,y3)->(x,y4)
'''
def yint(p1, p2, x, y3, y4):
	x1, y1 = p1
	x2, y2 = p2
	x3 = x
	x4 = x
	px = ((x1*y2 - y1*x2) * (x3 - x4) - (x1 - x2)*(x3*y4 - y3*x4)) / \
		 float((x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4))
	py = ((x1*y2 - y1*x2)*(y3-y4) - (y1 - y2)*(x3*y4 - y3*x4)) / \
			float((x1 - x2)*(y3 - y4) - (y1 - y2)*(x3-x4))
	return (px, py)

'''
Given three points a,b,c,
computes and returns the area defined by the triangle
a,b,c.
Note that this area will be negative
if a,b,c represents a clockwise sequence,
positive if it is counter-clockwise,
and zero if the points are collinear.
'''
def triangleArea(a, b, c):
        return (a[0]*b[1] - a[1]*b[0] + a[1]*c[0] \
                - a[0]*c[1] + b[0]*c[1] - c[0]*b[1]) / 2.0;

'''
Given three points a,b,c,
returns True if and only if
a,b,c represents a clockwise sequence
(subject to floating-point precision)
'''
def cw(a, b, c):
	return triangleArea(a,b,c) < EPSILON;
'''
Given three points a,b,c,
returns True if and only if
a,b,c represents a counter-clockwise sequence
(subject to floating-point precision)
'''
def ccw(a, b, c):
	return triangleArea(a,b,c) > EPSILON;

'''
Given three points a,b,c,
returns True if and only if
a,b,c are collinear
(subject to floating-point precision)
'''
def collinear(a, b, c):
	return abs(triangleArea(a,b,c)) <= EPSILON

'''
Given a list of points,
sort those points in clockwise order
about their centroid.
Note: this function modifies its argument.
'''
def clockwiseSort(points):
        # get mean x coord, mean y coord
        xavg = sum(p[0] for p in points) / len(points)
        yavg = sum(p[1] for p in points) / len(points)
        angle = lambda p:  ((math.atan2(p[1] - yavg, p[0] - xavg) + 2*math.pi) % (2*math.pi))
        points.sort(key = angle)

def divide(points):
        pass
        
def mergeHull(points):
        pass
'''
Given the list of points,
compute the hull using brute force
to give constant time and return
'''
def base_case(points):
        #sort points lowest to highest x coord and partition
        #print(points)
        xList =[x[0] for x in points]
        yList = [y[1] for y in points]
        #print(xList, yList)


        
        set1 = []
        for i in range(0, len(points)):

                for j in range(i+1, len(points)):
                        x1 = xList[i]
                        x2 = xList[j]
                        y1 = yList[i]
                        y2 = yList[j]
                        #print("x1: " + str(x1) + "\nx2: " + str(x2) + "\ny1: " + str(y1) + "\ny2: " + str(y2))

                        a1 = y1 - y2
                        b1 = x2 - x1
                        c1 = x1*y2 - y1*y2
                        above = 0
                        below = 0
                        for k in range(0, len(points)):
                                if a1 * xList[k] + b1 * yList[k] + c1 <= 0 :
                                        below+=1
                                elif a1*xList[k] + b1 * yList[k] + c1 >= 0:
                                        above+=1
                        if above == len(points) or below == len(points):
                                set1.append(tuple(points[i]))
                                set1.append(tuple(points[j]))
                #print(set1)
        clockwiseSort(set1)
        set1.reverse()
        return set1
        '''
        To be used later on for the part that isnt base case
    i=1
    j=i
    while(y(i,j+1) > y(i,j) or yint(i-1,j) > y(i,j)):
        if(y(i,j+1)>y(i,j))
            j+=1        |mod a|
        else:
            i-=1        |mod b|

        '''
##    while(yint(right_most, )
##        > yint(right_most, left_most,right_most[0],low_y,high_y))
##
##    return right

'''
Replace the implementation of computeHull with a correct computation of the convex hull
using the divide-and-conquer algorithm
'''
def computeHull(points):
        '''
                if base case if set <=5 brute force computation
                otherwise partition points into 2 sets A and B where
                        A consists of half the points with the lowest x coordinates
                        B consists of half the points with the highest x coordinates
                recursively compute 2 Hulls for A and B
                merge hulls by computing upper and lower tangents for HA and HB and discard
                        all points in between the two tangents

                Base case:
                        if size of points is <=5 or 6
                        use brute force to compute the hull
        '''
        if(len(points)<=5 or 6):
                return base_case(points)

        #sort points lowest to highest x coord and partition
        mid = math.floor(len(points)/2)
        left = points[:mid] # contains left hull
        right = points[mid:] #contains right hull
        leftHull = divide(left)
        rightHull = divide(right)

        return mergeHull(leftHull, rightHull)
        
        '''
        a = computeHull(left)
        b = computeHull(right)
        '''
        '''
        Finding Lower Tangent
                right_most is most right point of the left set
                left_most is the most left point of the right set
                while the bridge is not the lower tangent in a and b
                        while the bridge is not the lower tangent in a
                                clockwise turn right_most
                        while the bridge is not the lower tangent in b
                                counter clockwise turn the left_most
                return the bridge
        '''


        '''
        i=1
        j=i
        while(y(i,j+1) > y(i,j) or yint(i-1,j) > y(i,j)):
        if(y(i,j+1)>y(i,j))
                j+=1        |mod a|
        else:
                i-=1        |mod b|

        '''
        #return points
