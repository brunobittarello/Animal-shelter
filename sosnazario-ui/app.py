from flask import Flask, render_template, request
from data import *
import urllib.request, urllib.parse, json, logging
import glob

logging.basicConfig(level=logging.DEBUG)

app = Flask(__name__)

API_URL = "http://api:8080/api"

def getLabels():
	language = "pt"
	return  languages[language]

@app.route('/')
def index():
	labels = getLabels()
	return render_template("index.html", labels=labels)

# https://imasters.com.br/desenvolvimento/conhecendo-o-jinja2-um-mecanismo-para-templates-no-flask
@app.route('/search')
@app.route('/buscar')
def search():
	labels = getLabels()

	ages = Selection("age", labels.age_of_birth, [Option(2020,2020),Option(2021,2021),Option(2022,2022)])

	types = Selection("type", labels.type, [Option("D",labels.dogs),Option("C",labels.cats)])

	gender = Selection("gender", labels.gender, [Option("F",labels.female),Option("M",labels.male)])

	size = Selection("size", labels.size, [Option("S",labels.small),Option("M",labels.medium),Option("L",labels.large)])

	animals = []
	with urllib.request.urlopen(API_URL + "/animal/all") as url:
		animals = json.load(url)
		app.logger.info(animals)

	page = SearchPage([ages, types, gender, size], animals)
	return render_template("search.html", labels=labels, page=page)

@app.route('/query')
def query():
	args = request.args
	app.logger.info(args)
	app.logger.info(API_URL)
	# https://docs.python.org/3/howto/urllib2.html
	url_values = urllib.parse.urlencode(args)
	animals = []
	with urllib.request.urlopen(API_URL + "/animal/query?" + url_values) as url:
		animals = json.load(url)
		app.logger.info(animals)

	if not animals:
		app.logger.info("animalNotFound")
		labels = getLabels()
		return render_template("components/animalNotFound.html", labels=labels)
	return render_template("components/animalList.html", animals=animals)


if __name__ == '__main__':
	languages = {}
	language_list = glob.glob("languages/*.json")
	for lang in language_list:
		filename = lang.split('/')
		lang_code = filename[1].split('.')[0]

		with open(lang, 'r', encoding='utf8') as file:
			languages[lang_code] = json.loads(file.read(), object_hook=Struct)

	app.run(host='0.0.0.0', port=80)