import math
import sys
import copy
import timeit

EPSILON = sys.float_info.epsilon
start_time = timeit.default_timer()
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

'''
        Invariance for mergeHull
        Initialization: Given two lists of points that are split between and left and a right hull
        Maintenance: Find the upper tangent between the two hulls. Find the lower tangent of the two hulls.
                Then drop every point in between the two tangents. Merge.
        Termination: Returns the two hulls merged after dropping the points in between the two tangents
'''
def mergeHull(left,right):        
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

        nA = len(left)
        nB = len(right)
        #min y
        #max y needed for yint function
        yMin = left[0][1]
        print("\nyMin:",yMin)
        for y in left:
                #print("y[1]:", y[1],"\n")
                if yMin > y[1]:
                        yMin = y[1]
        for y in right:
                #print("right y[1]:", y[1],"\n")
                if yMin > y[1]:
                        yMin = y[1]
        yMin = 0
        yMax = left[0][1]
        for y in left:
                if yMax < y[1]:
                        yMax = y[1]
        for y in right:
                if yMax < y[1]:
                        yMax = y[1]
        
        #xMid for use in the yint function
        whole = left + right
        middle = math.floor(len(whole)/2)
        whole.sort(key=lambda x: x[0]) 
        #print(whole)
        first = whole[0][0]
        last = whole[len(whole)-1][0]
        xMid = (first + last)/2
        print(xMid)
        
        
##        print("\nyMin:", yMin)
##        print("\nleft:", left, "\nright:", right)
        i = 1 #keep track of left hull
        j = 1 #keep track of right hull
        #upper tangent
        while(yint(left[i], right[j+1], xMid, yMin, yMax) > yint(left[i], right[j], xMid, yMin, yMax) or yint(left[i-1], right[j], xMid, yMin, yMax) > yint(left[i], right[j], xMid, yMin, yMax)):
                if yint(left[i], right[j+1], xMid, yMin, yMax) > yint(left[i], right[j], xMid, yMin, yMax):
                        j = (j+1) % nB
                else:
                        i = (i - 1) % nA
        print("i", i,"\nj", j)
        upperTan = []
        tupleLeft = left[i]
        tupleRight = right[j]
        upperTan.append(tupleLeft)
        upperTan.append(tupleRight)
        print("\nupperTan:",upperTan)
        
##        print(upperTan) #0 x1 1 y1, 2 x2 3 y2

        k = 1
        m = 1
        while(yint(left[k], right[m+1], xMid, yMin, yMax) < yint(left[k], right[m], xMid, yMin, yMax) or yint(left[k-1], right[m], xMid, yMin, yMax) < yint(left[k], right[m], xMid, yMin, yMax)):
                if yint(left[k], right[m+1], xMid, yMin, yMax) < yint(left[k], right[m], xMid, yMin, yMax):
                        m = (m + 1) % nB
                else:
                        k = (k - 1) % nA
        print("k", k,"\nm", m)
        lowerTan = []
        tupleLeft = left[k]
        tupleRight = right[m]
        lowerTan.append(tupleLeft)
        lowerTan.append(tupleRight)
        print("\nlowerTan:",lowerTan)
        print("\nbefore pop(right):",right)
        print("\nbefore pop(left):",left)
        #traverse B list until we see b_m
        bCount = 0
        
        
        #drop everything from b_m to b_j
        for z in range(m, j):
                right.pop(z)
               
                       

        right.pop(len(right)-1)
        #drop everything from a_i to a_k
        for y in range(i, k):
                left.pop(y)

        left.pop(len(left)-1) #dropping dupe last element
        print("\nafter pop(right):",right)
        print("\nafter pop(left):",left)
        ret = left + right
        print("\nret:",ret)
        clockwiseSort(ret)
        print(ret)
        return ret
        
        
##                while (right.index[pointR] > m and right.index[pointR] < j):
##                        right[pointR].pop()
                #print(right.index[pointR])
        #for pointL in left:
##                while (left.index[pointL] > i and left.index[pointL] < k):
##                        left[pointL].pop()
        #print("left:",left, "\nright:",right)
        
'''
Given the list of points,
compute the hull using brute force
to give constant time and return
'''
'''
        Invariance for base_case
        Initialization: Our first pts list is empty therefore, the list is sorted initially. 
        Maintenance: As the program runs, this function computes the edges of a group of maximum of
                5 points. Checks to see if the rest points is below or above an edge case and if
                it is then it is an edge. 
        Termination: pts is an array filled with the edges of the input points list.
'''
def base_case(points):
        pts = []
        begin = points[0]
        minX = begin[0]

        '''
        Initialization:
                Given a set of points with a length less than 6,
                that set is already sorted by the x value in descending order
                minX is by default the first point
        Maintenance:
                As long as there are points left to check in the list continue
                to find the lowest x value
        Termination:
                Once we check the last point we should have minX assigned to the
                lowest value
        '''
        for i in points[1:]:
                if i[0]<minX:
                        minX=i[0]
                        begin = i
        point = begin
        pts.append(point)

        far_point = None

        '''
        Initialization:
                List of points, the first point in the hull and an empty set for
                the furthest point
        Maintenance:
                Given the beginning point being the only point in the hull keep
                checking
        Termination:
                Once the furthest point is the beginning point, we have made one
                full trip through all the points
        '''
        while far_point is not begin:
                p1 = None

                '''
                Initialization:
                        List of points, an empty set for the current point ; p1
                        and a beginning point ; point
                Maintenance:
                        p being the current point, keep checking points list from [0 - p-1]
                Termination:
                        At some point p, if the next point checked is not the beginning point
                        we have found the next point and breaks out the loop
                '''
                for p in points:
                        if p is not point:
                                p1 = p
                                break
                far_point = p1

                '''
                Initializtion:
                        List of points, the furthest point found so far and the
                        beginning point; point
                Maintenance:
                        p2 being the current point keep checking the next point from [0- p2-1]
                Termination:
                        At some point p2 we reach the end of the list
                '''

                for p2 in points:
                        if p2 is not point or p2 is not p1:
                                if ccw(point,far_point,p2):
                                        far_point = p2
                pts.append(far_point)
                point = far_point


        return pts

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


        #sort points lowest to highest x coord and partition
        points.sort(key=lambda x: x[0])
        #clockwiseSort(points)
        if(len(points)<=5):
                return base_case(points)

        #partition
        mid = math.floor(len(points)/2)
        left = points[:mid] # contains left hull
        right = points[mid:] #contains right hull
        leftHull = computeHull(left)
        rightHull = computeHull(right)
        
        return mergeHull(leftHull, rightHull)


print(timeit.default_timer() - start_time)

