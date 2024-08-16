import pandas as pd

pd.set_option("display.max_columns", None)

def prepare_df(df: pd.DataFrame) -> pd.DataFrame:
    df["fetched_at"] = df["fetched_at"].str[0:10].astype(str)
    df["is_ok"] = df.apply(lambda row: 1 if row.http_code == 200 else 0, axis=1)
    df["is_error"] = df.apply(lambda row: 1 if row.http_code != 200 else 0, axis=1)
    return df

def aggregate_df(df: pd.DataFrame) -> pd.DataFrame:
    return df.groupby("fetched_at").aggregate({
                "is_error": "sum",
                "is_ok": "sum",
                "user_agent": "nunique",
                "allow_cnt": "sum",
                "disallow_cnt": "sum"
            })

def aggregate_file(input_file_name: str, output_file_name: str):
    df = pd.read_csv(input_file_name)
    prepared_df = prepare_df(df)
    aggregated_df = aggregate_df(prepared_df)
    aggregated_df.to_csv(output_file_name, index=True)
