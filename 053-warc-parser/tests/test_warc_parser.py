from typing import List
from app.warc_parser import WarcParser
from app.const import key_fields
from app.writer import Writer

class InMemWriter(Writer):
    def __init__(self) -> None:
        super().__init__()
        self.data = []

    def write_row(self, row: str):
        self.data.append(row)

    def get_data(self) -> List[str]:
        return self.data    


def test_get_key_value():
    parser = WarcParser(None)

    test_value = "Hello!"

    for key_field in key_fields:
        string = f"{key_field} {test_value}"
        key, value = parser.get_key_value(string)
        assert key == key_field
        assert value == test_value

def test_process_line():
    in_mem_writer = InMemWriter()
    expected_result = [
        "2023-04-02T10:55:08Z,200,young.tonymctony.com,*,3,0\n",
        "2023-04-02T10:55:08Z,200,young.tonymctony.com,Mediapartners-Google,2,0\n"
    ]

    parser = WarcParser(in_mem_writer)
    with open("tests/test_case.txt") as file:
        for line in file:
            parser.process_line(line)
    parser.end_processing()
    actual_result = in_mem_writer.get_data()
    print(actual_result)
    assert expected_result == actual_result
