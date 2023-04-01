# Statistics Calculator

This project is a REST API for performing mathematical operations. It was created using Apache Maven and Typescript.

## Prerequisites

Before running this project, you should have the following installed on your machine:

- Java 8 or higher
- Apache Maven
- Node.js

## Installation

To install this project, follow these steps:

1. Clone the repository using `git clone https://github.com/andrewchatch/mathapi.git`
2. Navigate to the project directory using `cd mathapi`
3. Build the project using `mvn clean install`

## Usage

To use this project, follow these steps:

1. Start the server using `java -jar target/mathapi-0.0.1-SNAPSHOT.jar`
2. Navigate to `http://localhost:8080` in your web browser
3. Use the API to perform mathematical operations

## API

The Statistics API currently supports the following endpoints:

### Distributions

#### POST /distributions/binomial

This endpoint calculates a probability using the binomial distribution.

**Request Body**

```json
{
  "n": 5,
  "x": 3,
  "p": 0.4
}
```

**Sample Response**
```json
{
	"P(X > x)": 0.0870399999999999,
	"P(X = x)": 0.23040000000000005,
	"P(X < x)": 0.6825600000000001,
	"P(X ≤ x)": 0.31743999999999994,
	"P(X ≥ x)": 0.9129600000000001
}
```

#### POST /distributions/normal

This endpoint calculates a probability using the normal distribution.

**Request Body**
```json
{
    "x": 10,
    "popMean": 15,
    "popStDev": 6
}
```

**Sample Response**
```json
{
	"Z Score": -0.8333333333333334,
	"P(X > x)": 0.7996051529451687,
	"P(X < x)": 0.20039484705483135
}
```

#### POST /distributions/poisson

This endpoint calculates a probability using the poisson distribution.

**Request Body**
```json
{
    "x": 4,
    "lambda": 6
}
```

**Sample Response**
```json
{
	"P(X > x)": 0.7149434996833687,
	"P(X = x)": 0.1338526175399834,
	"P(X < x)": 0.15120388277664792,
	"P(X ≤ x)": 0.848796117223352,
	"P(X ≥ x)": 0.2850565003166313
}
```

#### POST /distributions/exponential

This endpoint calculates a probability using the exponential distribution.

**Request Body**
```json
{
    "x": 0.4,
    "lambda": 4
}
```

**Sample Response**
```json
{
	"P(X > x)": 0.20189651799465547,
	"P(X < x)": 0.7981034820053445
}
```

#### POST /distributions/geometric

This endpoint calculates a probability using the geometric distribution.

**Request Body**
```json
{
    "x": 7,
    "p": 0.5,
    "includesSuccess": true
}
```

**Sample Response**
```json
{
	"P(X > x)": 0.9375,
	"P(X = x)": 0.0078125,
	"P(X < x)": 0.0546875,
	"P(X ≤ x)": 0.9453125,
	"P(X ≥ x)": 0.0625
}
```

### Scores

#### POST /scores/z_score

This endpoint will calculate a Z score based on the input values.

**Request Body**
```json
{
    "x": 4,
    "popMean": 5,
    "popStDev": 0.7
}
```

**Sample Response**
```json
{
	"Z Score": -1.4285714285714286,
	"P(X > x)": 0.9290487150619141,
	"P(X < x)": 0.07095128493808589
}
```

#### POST /scores/t_score

This endpoint will calculate a T score based on the input values.

**Request Body**
```json
{
    "sampleMean": 6,
    "popMean": 10,
    "sampleStDev": 30,
    "sampleSize": 100
}
```

**Sample Response**
```json
{
	"T Score": -1.3333333333333333,
	"P(T < t)": 0.09274117500105655,
	"P(T > t)": 0.9072588249989435
}
```

### Summary Statistics

#### POST /summary_statistics

This endpoind will return 1-var summary statistics calculated for the entered list of numbers.

**Request Body**
```json
{
    "numbers": [1.0,2.0,3,3.6,9,12.4]
}
```

**Sample Response**
```json
{
	"Mean": 5.166666666666667,
	"Minimum": 1.0,
	"Maximum": 12.4,
	"Mode": 1.0,
	"Median": 3.3,
	"Standard Deviation": 4.506735699668516,
	"Sum": 31.0,
	"Count": 6.0,
	"Range": 11.4,
	"Variance": 20.31066666666667
}
```


## Contributing

If you would like to contribute to this project, please follow these steps:

1. Fork the repository
2. Create a new branch using `git checkout -b my-feature-branch`
3. Make changes and commit them using `git commit -am 'Add some feature'`
4. Push changes to your fork using `git push origin my-feature-branch`
5. Create a new pull request

## License

This project is licensed under the MIT License - see the `LICENSE` file for details.
