from flask import Blueprint

main=Blueprint('main', __name__)

# 从当前目录导入
from . import  views, errors