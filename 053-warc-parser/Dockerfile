FROM python:3.10

WORKDIR /app

COPY requirements.txt .

RUN python3 -m pip install -r requirements.txt

COPY . .

ENTRYPOINT ["python3", "app/run.py"]
