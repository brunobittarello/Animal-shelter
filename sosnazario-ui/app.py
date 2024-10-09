from flask import Flask, render_template
import urllib.request, json, logging

logging.basicConfig(level=logging.DEBUG)

app = Flask(__name__)

class SearchPage:
	def __init__(self, fields, animals):
		self.fields = fields
		self.animals = animals

class Selection:
	def __init__(self, id, name, options=list()):
		self.id = id
		self.name = name
		self.options = options

class Option:
	def __init__(self, value, name):
		self.value = value
		self.name = name

class Animal:
	def __init__(self, name):
		self.name = name

@app.route('/')
def index():
	return render_template("index.html")

# https://imasters.com.br/desenvolvimento/conhecendo-o-jinja2-um-mecanismo-para-templates-no-flask
@app.route('/search')
@app.route('/buscar')
def search():
	ages = Selection("age", "Ano", [Option(2020,2020),Option(2021,2021),Option(2022,2022)])

	types = Selection("type", "Tipo", [Option(1,"Cães"),Option(2,"Gatos")])

	genres = Selection("genre", "Gênero", [Option(1,"Femea"),Option(2,"Macho")])

	size = Selection("size", "Porte", [Option(1,"P"),Option(2,"M"),Option(3,"G")])

	animals = []
	with urllib.request.urlopen("http://api:8080/api/animal/all") as url:
		animals = json.load(url)
		app.logger.info(animals)

	page = SearchPage([ages, types, genres, size], animals)
	return render_template("search.html", page=page)

if __name__ == '__main__':
	app.run(host='0.0.0.0', port=80)