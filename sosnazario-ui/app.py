from flask import Flask, render_template
from data import *
import urllib.request, json, logging
import glob

logging.basicConfig(level=logging.DEBUG)

app = Flask(__name__)

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

	types = Selection("type", labels.type, [Option(1,labels.dogs),Option(2,labels.cats)])

	genres = Selection("genre", labels.genre, [Option(1,labels.female),Option(2,labels.male)])

	size = Selection("size", labels.size, [Option(1,labels.small),Option(2,labels.medium),Option(3,labels.large)])

	animals = []
	with urllib.request.urlopen("http://api:8080/api/animal/all") as url:
		animals = json.load(url)
		app.logger.info(animals)

	page = SearchPage([ages, types, genres, size], animals)
	return render_template("search.html", labels=labels, page=page)

if __name__ == '__main__':
	languages = {}
	language_list = glob.glob("languages/*.json")
	for lang in language_list:
		filename = lang.split('/')
		lang_code = filename[1].split('.')[0]

		with open(lang, 'r', encoding='utf8') as file:
			languages[lang_code] = json.loads(file.read(), object_hook=Struct)

	app.run(host='0.0.0.0', port=80)