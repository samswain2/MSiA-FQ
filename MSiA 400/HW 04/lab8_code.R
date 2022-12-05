library(quantmod); library(dplyr)
tickers = c("AAPL", "AMGN", "AXP", "BA", "CAT", "CRM", "CSCO",
            "CVX", "DIS", "KO", "GS", "HD", "HON", "IBM", "INTC",
            "JNJ", "JPM", "MCD", "MMM", "MRK", "MSFT", "NKE", 
            "PG", "TRV", "UNH", "VZ", "V", "WMT", "WBA")
stocks = lapply(tickers, getSymbols, from="2019-01-01",
                to="2019-12-31", auto.assign=FALSE)
stocks = data.frame(Reduce(function(df1, df2)
  merge(df1, df2,by=0, all.x=T), stocks))
stocks = select(stocks, contains("Adjusted"))
colnames(stocks) = tickers
stocks[1:10,1:5]

library(missForest)
set.seed(400)
# randomly replace 10% of values with NAs
stocks.na = prodNA(stocks, noNA=0.1)
stocks.na[1:5,1:5]

# check missing data
is.na(stocks.na[1:5,1:5])

AAPL.na = stocks.na$AAPL
AAPL.na[1:10]
# disregard missing data
mean(AAPL.na, na.rm=T)
# remove missing observations
AAPL.omit = na.omit(AAPL.na)
AAPL.omit[1:10]


# random sampling
random.imp = function (a){
  missing = is.na(a)
  n.missing = sum(missing) # number of missing values
  a.obs = a[!missing]
  imputed = a
  # sample with replacement
  imputed[missing] = sample(a.obs, n.missing, replace=T)
  return(imputed)
}
AAPL.rndimp = random.imp(AAPL.na)
AAPL.rndimp[1:10]


# most common
x = c(1,1,NA,3,4,4,5,5,5,5,6,NA)
# compute the mode
Mode = function(x) {
  mode = as.numeric(names(sort(table(x),decreasing=T))[1])
  return(mode)
} 
Mode(x)
mcv.imp = function (a){
  missing = is.na(a)
  imputed = a
  imputed[missing] = Mode(a)
  return(imputed)
}
x.mcv = mcv.imp(x)
x.mcv

# avg imputation
avg.imp = function (a){
  missing = is.na(a)
  imputed = a
  imputed[missing] = mean(a, na.rm=T)
  return(imputed)
}
AAPL.avgimp = avg.imp(AAPL.na)
AAPL.avgimp[1:10]

# last value
AAPL.last = na.locf(AAPL.na)
AAPL.last[1:10]

# linear
n = length(AAPL.na)
AAPL.linear = approxfun(1:n, AAPL.na)(1:n)
AAPL.linear[1:10]

# knn
library(DMwR)
stocks.1NN = knnImputation(stocks.na,k=1)
stocks.1NN[1:5,1:5]
stocks.5NN = knnImputation(stocks.na,k=5)
stocks.5NN[1:5,1:5]

# MICE
library(mice)
md.pattern(stocks.na[1:5,1:5])
stocks.mice = mice(stocks.na, seed=400, print=F)
stocks.mice$imp$AAPL[1:5]
stocks.miceImp = complete(stocks.mice, 5)
stocks.miceImp[1:5,1:5]


