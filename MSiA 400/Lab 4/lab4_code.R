# hold out
gradAdmit = read.csv('gradAdmit.csv')
set.seed(400)
n = nrow(gradAdmit) # number of samples
# hold out 20% for testing
sample = sample.int(n = n, size = floor(.2*n), replace = F) 
train = gradAdmit[-sample,]
test = gradAdmit[sample,]

# k-fold CV
library(caret)
nfolds = 5
folds = createFolds(1:n, k=nfolds) 
for (i in 1:nfolds){
  train = gradAdmit[-folds[[i]],] 
  test = gradAdmit[folds[[i]],]
  # Train & analyze model
}

# svm
library(e1071)
# https://www.rdocumentation.org/packages/e1071/versions/1.7-2/topics/svm
svm = svm(factor(admit)~gre+gpa, kernel = "linear", data=train[1:10, ], scale=FALSE) 
summary(svm)
plot(svm, train[1:10,], gre~gpa)
pred = predict(svm, newdata=test, type='response') 
pred[1:15]
beta = t(svm$coefs)%*%svm$SV
beta0 = svm$rho
plot(train[1:10, c(3,2)])
abline(beta0 / beta[1], -beta[2] / beta[1])



svm = svm(factor(admit)~., data=train) 
summary(svm)
plot(svm, train, gre~gpa)
pred = predict(svm, newdata=test, type='response') 
pred[1:15]


