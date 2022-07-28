import random
import time
from kafka import KafkaProducer

producer = KafkaProducer(bootstrap_servers='localhost:9092')

ids = ["Wroclaw", "Krakow", "Warsaw", "Gdansk", "Poznan"]
max_amount = 10

def send(text: str):
    producer.send('input00', key=b'my-key', value=text.encode())
    producer.flush()
    print("sent:" + text)

def start_generation():
    i = 0
    while i<60:
        i=i+1
        id: str = ids[random.randint(0,len(ids)-1)]
        amount: int = random.randint(1, max_amount)
        text = '{"id":"' + id + '", "amount":'+ str(amount) +'}'
        send(text)
        time.sleep(1)
    





def main():
    # send("Hello world !")
    start_generation()


if __name__ == "__main__":
    main()