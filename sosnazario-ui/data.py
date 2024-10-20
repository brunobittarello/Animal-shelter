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

class Struct:
	def __init__(self, entries):
		self.__dict__.update(entries)