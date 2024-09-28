from fastapi import FastAPI
from routes import router
from utils.logger import setup_logging

app = FastAPI()

setup_logging()

app.include_router(router)

@app.get("/")
def read_root():
    return {"message": "Welcome to the Movie Recommendation System API!"}
