from fastapi import FastAPI
from pydantic import BaseModel
from services.generate_report import generate_report

app = FastAPI()

class ReportRequest(BaseModel):
    report_type: str
    model_metrics: dict

@app.post("/generate-report/")
async def generate_report_endpoint(req: ReportRequest):
    output_path = f"reports/{req.report_type}.html"
    generate_report(req.model_metrics, output_path)
    return {"message": "Report generated successfully", "path": output_path}
