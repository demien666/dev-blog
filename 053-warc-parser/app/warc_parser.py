from typing import Tuple
from writer import Writer
from csv_row import CsvRow
from const import key_fields

class WarcParser:
    request_start = "WARC-Type: request"
    response_start = "WARC-Type: response"
    
    def __init__(self, writer: Writer) -> None:
        self.is_request = False
        self.is_response = False
        self.extracted = CsvRow()
        self.writer = writer

    def get_key_value(self, line: str) -> Tuple[str, str]:
        for key in key_fields:
            if line.upper().startswith(key.upper()):
                value = line[len(key):].strip()
                return key, value
        return None, None
        
    def process_line(self, line: str):
        if WarcParser.request_start in line:
            self.is_response = False

        if WarcParser.response_start in line:
            self.is_response = True
            self.write_extracted()

        if self.is_response:
            key, value = self.get_key_value(line)
            if key and value:
                self.extracted.set(key, value)

            # for key in key_fields:
            #     if line.upper().startswith(key.upper()):
            #         value = line[len(key):].strip()
            #         # print(f"Extracted {value} for {key}")
            #         self.extracted.set(key, value)

    def write_extracted(self) -> None:
        for csv_line in self.extracted.to_string_list():
            self.writer.write_row(csv_line)

        self.extracted = CsvRow()
    
    def end_processing(self):
        if self.is_response:
            self.write_extracted()

