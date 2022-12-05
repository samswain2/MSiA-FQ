data(iris); iris$Species = factor(iris$Species)
head(iris)
par(cex=0.7); sl=iris$Sepal.Length
# histogram
hist(sl)
# stem and leaf
stem(sl, width=75)
# box plot
par(cex=0.7)
boxplot(iris[,1:4], range=0)
# box and whisker
par(cex=0.7)
boxplot(iris[,1:4])
# 5 statistics
summary(iris[,1:4])
library(e1071)
skewness(sl)
kurtosis(sl)
# pie chart
par(cex=0.7)
t=table(iris$Species)
pie(t,labels=names(t),col=rainbow(length(t)))
# qq plot
par(cex=0.7); qqnorm(sl)
abline(mean(sl),b=sd(sl),col="red")
# Outliers
library(ggplot2); library(ggpmisc)
ggplot(data=iris, aes(Sepal.Length, Sepal.Width, color=Species)) +
  geom_point(size=.5) +
  stat_dens2d_filter(geom="point", shape=4, keep.fraction=.1)
# kmeans
set.seed(400)
km = kmeans(iris[,1:4], 3) # 3 clusters 
km$size # size of each cluster
km$centers # center of each cluster
km$withinss # within cluster sum-of-squares
km$betweenss/km$totss 
# result
iris$cluster=factor(km$cluster)
ggplot(data=iris, aes(Sepal.Length, Sepal.Width, color=cluster)) +
  geom_point(size=.5)
# SOM
library(kohonen)
som_grid = somgrid(xdim=5, ydim=5, topo="hexagonal")
som_model = som(scale(iris[,1:4]), grid=som_grid)
par(cex=0.5); plot(som_model)
