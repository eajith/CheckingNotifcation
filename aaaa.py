import math
import copy
''' checks for prelims test '''
def firstCheck(n , c , tree_details):
  count = 0;
  for k in range(0,n):
    diff = 0
    monkeyintree = tree_details[k][2]
    capacityoftree = tree_details[k][3]
    diff = capacityoftree - monkeyintree
    if diff < 0:
      count= count+1
      node = k
    if count == 2:
      return 'False'
  if count == 1:
    return node
  else:
   return 'true'
   
''' distance matrix generator find whehter the node are connected or not '''
def distancematrixgenerator(n , c , tree_details):
  connect_matrix = []
  for i in range(0,n):
    xcor = tree_details[i][0]
    ycor = tree_details[i][1]
    temp_connect_in = []
    for j in range(0,n):
      xcor1 = tree_details[j][0]
      ycor1 = tree_details[j][1]
      dist = math.sqrt((xcor1-xcor)(xcor1-xcor)+(ycor1-ycor)(ycor1-ycor))
      if  dist > c:
        temp_connect_in.append(0)
      elif dist == 0:
        temp_connect_in.append(0)
      elif dist < c:
        temp_connect_in.append(1)
    connect_matrix.append(temp_connect_in)
  return connect_matrix
   
 
''' check for unique end point '''
def checkendpoint(endpoint,distmatrix,n,c,tree_details1,totcount):
  temp_tree_details = copy.deepcopy(tree_details1)
  
  temp_endpoint = []
  temp_endpoint.append(endpoint)
  connected_node = []
  donevertices = []
  newendpoint=[]
  donevertices.append(endpoint)
  reachedmonkeycount = temp_tree_details[endpoint][2]
  monkeynotreachedcount = totcount - reachedmonkeycount
  for l in range(0,n):
    if distmatrix[endpoint][l] == 1:
      connected_node.append(l)
    else:
      continue
  remaningcapacity = 0
  ''' for all connected_node move the monkey to the endpoint tree '''

  for k in connected_node:
    temp_tree_details[k][3] = temp_tree_details[k][3] - temp_tree_details[k][2]
    remaningcapacity =  remaningcapacity + temp_tree_details[k][3]
    reachedmonkeycount = reachedmonkeycount + temp_tree_details[k][2]
    monkeynotreachedcount = monkeynotreachedcount - temp_tree_details[k][2]
    donevertices.append(k)
    temp_tree_details[k][2] = 0
    newendpoint.append(k)
  neededcapacity=0
  ''' check for feasibility to cross the  next level tree with the remaning monkey '''
  for j in range(0,n):
    if j not in donevertices:
      neededcapacity = neededcapacity+temp_tree_details[j][3]
  if remaningcapacity < neededcapacity:
    return -1
  elif reachedmonkeycount == totcount:
    return 'yes'
  else:
    connected_node=[]
    while reachedmonkeycount != totcount and len(newendpoint) != 0:
      tempnewendpoint = []
      for m in newendpoint:
        for l in range(0,n):
          if distmatrix[m][l] == 1:
            connected_node.append(l)
          else:
            continue
        ''' check for the connected_node move the monkey to endpoint tree'''
        for k in connected_node:
           if k not in donevertices:
            if temp_tree_details[m][3] >= temp_tree_details[k][2]:
               temp_tree_details[k][3] = temp_tree_details[k][3] - temp_tree_details[k][2]
               remaningcapacity =  remaningcapacity + temp_tree_details[k][3]
               reachedmonkeycount = reachedmonkeycount + temp_tree_details[k][2]
               monkeynotreachedcount = monkeynotreachedcount - temp_tree_details[k][2]
               donevertices.append(k)
               temp_tree_details[k][2] = 0
               tempnewendpoint.append(k)
               neededcapacity=0
            else:
               return -1
            ''' check for feasibility to cross the  next level tree with the remaning monkey '''
            for j in range(0,n):
              if j not in donevertices:
                neededcapacity = neededcapacity+temp_tree_details[j][3]
            if remaningcapacity < neededcapacity:
              return -1
            elif reachedmonkeycount == totcount:
              return 'yes'
        newendpoint=tempnewendpoint
  
 
  
  return -1
   
  
      
def main():
  n , c = input().split()
  n = int(n)
  c = float(c)
  tree_details = []
  solutionflag = False
  solution = ""
  totcount = 0
  for i in range(0,n):
    temp = [int(k) for k in input().split()]
    tree_details.append(temp)
  ''' checking whether there is no possible solution based on the capacity and no of monkeys available '''
  firstCheck_status = firstCheck(n , c , tree_details)
  ''' total monkey count '''
  for z in range(0,n):
    totcount = tree_details[z][2] + totcount
  ''' generate connected_node matrix '''
  distancematrix=distancematrixgenerator(n, c, tree_details)
  ''' print and process based on the prilims check '''
  if firstCheck_status == 'False':
    print(-1)
  elif firstCheck_status == 'true':
    for i in range(0,n):
      checkstatus1 = checkendpoint(i,distancematrix,n,c,tree_details,totcount)
      if checkstatus1 == 'yes': 
        solutionflag = True
        solution = solution+" "+str(i)
    if solutionflag == False:
      print(-1)
    elif solutionflag == True:
      print(solution)
  else:
    checkstatus2 = checkendpoint(firstCheck_status,distancematrix,n,c,tree_details,totcount)
    print(firstCheck_status)
    '''print('yes')'''
 
main()