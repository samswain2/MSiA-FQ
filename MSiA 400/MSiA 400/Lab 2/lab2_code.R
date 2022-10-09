# Import transition matrix
P = as.matrix(read.csv("baseball.csv", header=T, row.names=1))
P[1:4,1:4]

a = c(1,rep(0,24)) #initial state 
a[1:20]

# matrix multiplication uses %*%
prob5 = a %*% P %*% P %*% P %*% P %*% P
prob5[25]

#alternatively, use expm
library(expm) 
prob5 = a %*% (P %^% 5)
prob5[25]

# half-inning over n batters
x=rep(0,10); at=a 
for (i in 1:10){
  at = at %*% P
  x[i] = at[25] }
par(cex=0.7); plot(x, type="o")


# equilibrium
P[25,1]=1; P[25,25]=0 #make the network irreducible 
Q=t(P)-diag(25)
Q[25,]=rep(1,25)
rhs=c(rep(0,24),1)
Pi=solve(Q,rhs) #solves Q %*% Pi = rhs for Pi 
Pi[1:4]


# path probability
pathprob = P[1,9]*P[9,10]*P[10,18]*P[18,23]*P[23,25]
pathprob

# mean first passage time
B=P[1:24,1:24]
Q=diag(24)-B
rhs=rep(1,24)
m=solve(Q,rhs)
m[1]
