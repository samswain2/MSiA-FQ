#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pandas as pd


# In[2]:


import sqlite3


# In[3]:


conn = sqlite3.connect("hw5.db")


# In[4]:



hw5_db = pd.read_csv("hw5_original.csv")
hw5_db.to_sql("hw5_db", conn, if_exists = "replace", index = False)


# In[5]:


pd.read_sql("""
select * from hw5_db
""", conn)

