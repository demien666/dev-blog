from app.csv_row import CsvRow
import app.const

def test_base_set():
    row = CsvRow()

    row.set_fetched_at("123")
    assert row.fetched_at == "123"

    row.set_http_code("200 OK")
    assert row.http_code == "200"

    row.set_http_code("404 Not Found")
    assert row.http_code == "404"

    row.set_domain("http://www.turismoruidera.info/robots.txt")
    assert row.domain == "www.turismoruidera.info"

    row.set_domain("https://www.upseducation.in/robots.txt")
    assert row.domain == "www.upseducation.in"    

def test_agent_allow_disallow():
    row = CsvRow()
    row.add_user_agent("A")
    row.add_allow()
    row.add_disallow()
    row.add_disallow()

    row.add_user_agent("B")
    row.add_allow()

    row.add_user_agent("C")
    row.add_disallow()

    assert row.allow == {
        "A": 1,
        "B": 1,
        "C": 0
    }

    assert row.disallow == {
        "A": 2,
        "B": 0,
        "C": 1
    }

def test_main_set():
    row = CsvRow()
    row.set(app.const.KEY_HTTP_CODE, "HC")
    row.set(app.const.KEY_FETCHET_AT, "AT")
    row.set(app.const.KEY_DOMAIN, "DM")
    row.set(app.const.KEY_RECORD_ID, "ID")

    row.set(app.const.KEY_USER_AGENT, "A1")
    row.set(app.const.KEY_ALLOW, "A1.A1")
    row.set(app.const.KEY_DISALLOW, "A1.D1")

    row.set(app.const.KEY_USER_AGENT, "A2")
    row.set(app.const.KEY_ALLOW, "A2.A1")

    csv_rows = row.to_string_list()
    assert len(csv_rows) == 2
    assert csv_rows[0] == "AT,HC,DM,A1,1,1\n"
    assert csv_rows[1] == "AT,HC,DM,A2,0,1\n"
