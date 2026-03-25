# 3D Wireframe Renderer
This project is a Java implementation of a 3D wireframe renderer. It constructs, rotates and projects 3D models onto a 2D screen. This project is heavily inspired by [Tsoding's tutorial "One Formula That Demystifies 3D Graphics"](https://github.com/tsoding/formula), which was originally implemented in JavaScript.
## Features
- 3D Wireframe Rendering. Visualize simple 3D models as wireframes.
- Rotation and scaling (Not configurable yet).


## Screenshots

## Usage
#### 1. Clone the repository
```
git clone https://github.com/nikkcom/3d-wireframe-render.git
cd 3d-wireframe-render
```

#### 2. Compile and run

Compile as usual
```
javac -d out src/main/java/me/niko/*.java src/main/java/me/niko/models/*.java src/main/java/me/niko/models/shapes/*.java src/main/java/me/niko/view/*.java
```

Run the program with options
```
java -cp out me.niko.Main [options]
```

#### 3. A window will appear, displaying the model.

### Command Line Options
Options to change the color or the shape of the model.

| Flag         | Description                     | Values |
|--------------|---------------------------------|--------------|
| -s, --shape  | Select the 3D model shape to render | `cube` or `diamond` |
| -c, --color  | Set the model color             | Hex code in `#RRGGBB` format|

Default red cube
```
java -cp out me.niko.Main
```

White diamond shape
```
java -cp out me.niko.Main --shape diamond --color '#ffffff'
```

Blue cube shape
```
java -cp out me.niko.Main --shape cube --color '#0000ff'
```


