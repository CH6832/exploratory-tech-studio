from pydantic import BaseModel
from typing import Dict, Any

class ReportRequest(BaseModel):
    report_type: str
    model_metrics: Dict[str, Any]
