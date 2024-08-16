import pandas as pd
import copy
from app.aggregator import prepare_df, aggregate_df

def test_prepare_df():
    input_dict = {    
        "fetched_at": "01.01.2024Tsome seconds",
        "http_code": 200,
        "domain": "domain1",
        "user_agent": "user_agent1",
        "disallow_cnt": 0,
        "allow_cnt": 1
    }
    
    # ok case
    input_df = pd.DataFrame([input_dict])
    actual_result = prepare_df(input_df).to_dict("records")[0]
    assert actual_result["is_ok"] == 1
    assert actual_result["is_error"] == 0
    assert actual_result["fetched_at"] == "01.01.2024"
    
    # error case
    input_dict["http_code"] = 404
    input_df = pd.DataFrame([input_dict])
    actual_result = prepare_df(input_df).to_dict("records")[0]
    assert actual_result["is_ok"] == 0
    assert actual_result["is_error"] == 1

def get_prepared(fetched_at: str, 
                 is_error: int,
                 is_ok: int,
                 user_agent: str,
                 allow: int,
                 disallow: int):
    return {
        "fetched_at": fetched_at,
        "is_error": is_error,
        "is_ok": is_ok,
        "user_agent": user_agent,
        "allow_cnt": allow,
        "disallow_cnt": disallow
    }

def test_aggregate():
    input = [
        get_prepared("01.01.2024", 1, 1, "a1", 1, 1),
        get_prepared("01.01.2024", 0, 0, "a1", 0, 0),
        get_prepared("01.01.2024", 1, 1, "a2", 1, 1),
        get_prepared("02.01.2024", 0, 0, "a2", 0, 0)
    ]
    input_df = pd.DataFrame(input)
    actual = aggregate_df(input_df).to_dict("records")
    actual_d1 = actual[0]
    assert actual_d1["is_error"] == 2
    assert actual_d1["is_ok"] == 2
    assert actual_d1["user_agent"] == 2
    assert actual_d1["allow_cnt"] == 2
    assert actual_d1["disallow_cnt"] == 2

    actual_d2 = actual[1]
    assert actual_d2["is_error"] == 0
    assert actual_d2["is_ok"] == 0
    assert actual_d2["user_agent"] == 1
    assert actual_d2["allow_cnt"] == 0
    assert actual_d2["disallow_cnt"] == 0
