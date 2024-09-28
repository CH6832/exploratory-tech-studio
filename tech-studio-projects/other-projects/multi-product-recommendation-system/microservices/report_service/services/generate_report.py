import pandas as pd
from jinja2 import Environment, FileSystemLoader

def generate_performance_report(model_metrics, output_path):
    # Load a template
    env = Environment(loader=FileSystemLoader('templates'))
    template = env.get_template('performance_report.html')

    # Render the template with model metrics
    rendered_report = template.render(metrics=model_metrics)

    # Save the rendered report to a file
    with open(output_path, 'w') as f:
        f.write(rendered_report)

def generate_data_summary(dataframe, output_path):
    # Create a summary report
    summary_stats = dataframe.describe()

    with open(output_path, 'w') as f:
        f.write("# Data Summary Report\n")
        f.write(summary_stats.to_string())
