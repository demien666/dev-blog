import gzip
import os
from writer import FileWriter
from warc_parser import WarcParser
import aggregator

def process_file(file_name: str, parser: WarcParser) -> None:
    print(file_name)
    with gzip.open(file_name,'rt', errors="ignore") as f:
        for line in f:
            parser.process_line(line)
    parser.end_processing()

def process_folder(folder_name: str) -> None:
    files = os.listdir(f"{folder_name}/raw")
    for file_name in files:
        full_path = folder_name + "/raw/" + file_name
        print(f"Processing {full_path}")
        extracted_file_name = f"{folder_name}/extracted/{file_name}.csv"
        writer = FileWriter(extracted_file_name)
        process_file(full_path, WarcParser(writer))
        stats_file_name = f"{folder_name}/stats/{file_name}.csv"
        aggregator.aggregate_file(extracted_file_name, stats_file_name)

if __name__ == '__main__':
    process_folder("data")
