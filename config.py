import os
# 获取当前绝对路径
base_dir=os.path.abspath(os.path.dirname(__file__))

class Config:
    SECRET_KEY=os.environ.get('SECRET_KEY') or 'you never guess my password'
    SQLALCHEMY_COMMIT_ON_TEARDOWM=True
    BLOG_MAIL_SUBJECT_PREFIX='[Blog]'
    BLOG_MAIL_SENDER='Abel Huang <abelhaung@abel.com>'
    BLOG_ADMIN=os.environ.get('BLOG_ADMIN')

    @staticmethod
    def init_app(app):
        pass

class DevelopmentConfig(Config):
    DEBUG=True
    MAIL_SERVER='smtp.googlemail.com'
    MAIL_PORT=587
    MAIL_USE_TLS=True
    MAIL_USERNAME=os.environ.get('MAIL_USERNAME')
    MAIL_PASSWORD = os.environ.get('MAIL_PASSWORD')
    # 首先会在环境变量中寻找， 如果未找到则会直接使用默认数据库
    SQLALCHEMY_DATABASE_URI=os.environ.get('DEV_DATABASE_URL') or 'mysql://root@localhost:3306/flask-blog'

class TestingConfig(Config):
    TESTING = True
    SQLALCHEMY_DATABASE_URI = os.environ.get('TEST_DATABASE_URL')

class ProductionConfig(Config):
    SQLALCHEMY_DATABASE_URI = os.environ.get('DATABASE_URL')

config={
    'development':DevelopmentConfig,
    'testing':TestingConfig,
    'production':ProductionConfig,
    'default':DevelopmentConfig
}

