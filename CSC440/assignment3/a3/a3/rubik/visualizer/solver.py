import rubik

def shortest_path(start, end):
    """
    Using 2-way BFS, finds the shortest path from start_position to
    end_position. Returns a list of moves. 
    Assumes the rubik.quarter_twists move set.
    """
    # if start is end then just return because that means the cube is solved
    if start == end:
        return []
    
    sPaths = {start: None}
    ePaths = {end: None}

    sPathInd = {}
    ePathInd = {}
    rotStart = {}
    rotEnd = {}

    startFrontier = [start]
    endFrontier = [end]

    d = 1
    i, j = 0
    found  = False

    while depth < 8:
        newStart = []
        newEnd = []

        for u in startFrontier:
            for v in rubik.quarter_twists:
                newState = rubik.perm_apply(v,u)
                if newState not in sPaths:
                    sPath[newState] = u
                    sPathInd[newState] = i
                    rotStart[i] = v
                    newStart.append(newState)
                    i += 1
                    if newState in ePaths:
                        found = True
                        pathInt = newState
                        break
            if found:
                break
        if found:
            break
        startFrontier = newStart
        for u in endFrontier:
            for v in rubik.quarter_twists:
                newState = rubik.perm_apply(v,u)

                if newState not in ePaths:
                    ePaths[newState] = u
                    ePathInd[newState] = j
                    rotEnd[j] = rubik.perm_inverse(v)
                    newEnd.append(newState)
                    if newState in sPaths:
                        found = True
                        pathInt = newState
                        break
            if found:
                break
        if found:
            break
        endFrontier = newEnd
        depth += 1
    
    if depth>=8:
        return None

    retList = []
    tPathInt = pathInt
    while tPathInt != start:
        index = sPathInd[tPathInt]
        move = rotStart[index]
        retList.append(move)
        tPathInt = sPaths[tPathInt]
    retList.reverse()

    tPathInt = pathInt

    while tPathInt != end:
        index = ePathInd[tPathInt]
        move = rotEnd[index]
        retList.append(move)
        tPathInt = ePaths[tPathInt]


    return retList
