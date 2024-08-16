import const
from dataclasses import dataclass
from typing import List

OK = 200

@dataclass
class CsvRow:

    def __init__(self) -> None:
        self.fetched_at = None
        self.http_code = None
        self.domain = None
        self.record_id = None

        self.agents = []
        self.last_agent = None
        self.allow = {}
        self.disallow = {}

    def to_string_list(self) -> List[str]:
        result = []
        for agent in self.agents:
            result.append(f"{self.fetched_at},{self.http_code},{self.domain},{agent},{self.disallow[agent]},{self.allow[agent]}\n")
        return result

    def set_fetched_at(self, value: str) -> None:
        self.fetched_at = value

    def add_user_agent(self, agent_name: str) -> None:
        # to avoid bot-traffic,bottraffic,trafficbot,traffic-bot
        agent_name = agent_name.replace(","," ")
        self.agents.append(agent_name)
        self.last_agent = agent_name
        self.allow[agent_name] = 0
        self.disallow[agent_name] = 0

    def check_empty_agent(self):    
        if not self.last_agent:
            self.last_agent = ""
            self.add_user_agent("") 

    def add_allow(self) -> None:
        self.check_empty_agent()        
        self.allow[self.last_agent] += 1

    def add_disallow(self) -> None:
        self.check_empty_agent()    
        self.disallow[self.last_agent] += 1

    def set_http_code(self, value: str) -> None:
        self.http_code = value.split(" ")[0]

    def set_domain(self, value: str) -> None:
        extra = ["<", ">", "http://", "https://"]
        tmp = value
        for el in extra:
            tmp = tmp.replace(el, "")
        self.domain = tmp.split("/")[0]  


    def set(self, key: str, value: str) -> None:
        try:
            match(key):
                case const.KEY_RECORD_ID:
                    self.record_id = value
                case const.KEY_FETCHET_AT:
                    self.fetched_at = value
                case const.KEY_DOMAIN:
                    self.set_domain(value)
                case const.KEY_HTTP_CODE:
                    self.set_http_code(value)
                case const.KEY_USER_AGENT:
                    self.add_user_agent(value)
                case const.KEY_ALLOW:
                    self.add_allow()
                case const.KEY_DISALLOW:
                    self.add_disallow()

        except Exception as ex:
            print(f"Problem with record_id:{self.record_id}, {str(ex)}")
            raise
