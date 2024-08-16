class Writer:
    def write_row(self, row: str):
        print(row)

class FileWriter(Writer):
    def __init__(self, file_name: str) -> None:
        self.file_name = file_name
        self.write_row("fetched_at,http_code,domain,user_agent,disallow_cnt,allow_cnt\n")

    def write_row(self, row: str):
        with open(self.file_name, "a") as csv_file:
            csv_file.write(row)
