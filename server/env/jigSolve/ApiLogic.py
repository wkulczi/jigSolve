class ApiLogic:
    
    def __init__(self, puzzles=None, puzzle=None):
        self.one_puzzle_picture = puzzle
        self.entire_puzzles_picture = puzzles
        print('Heres where magic happens')

    def set_one_puzzle_picture(self, puzzle):
        print('setting one puzzle pic')

    def set_entire_puzzles_picture(self, puzzle):
        print('setting entire puzzles pic')

    # then do some magic and return a pic
    def generate_picture_with_positioned_puzzle(self):
        print('generating picture with positioned puzzle')
