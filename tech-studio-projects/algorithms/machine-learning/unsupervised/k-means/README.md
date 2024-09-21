# K-Means Algorithm

### **Algorithm Overview:**

1. **Initialization**: Select \( k \) initial centroids randomly from the data points. These centroids represent the center of each cluster.
2. **Assignment Step**: Assign each data point to the nearest centroid based on a distance metric, typically Euclidean distance. This forms \( k \) clusters.
3. **Update Step**: Recalculate the centroids as the mean of all data points assigned to each centroid.
4. **Repeat**: Repeat the assignment and update steps until the centroids no longer change significantly or a maximum number of iterations is reached.

### **Characteristics:**

- **Type**: K-means is a **partitioning method** and a **centroid-based algorithm**.
- **Distance Metric**: It commonly uses Euclidean distance, but other distance measures can be used depending on the application.
- **Objective Function**: It minimizes the sum of squared distances between data points and their respective cluster centroids (within-cluster sum of squares).

### **Pros:**

- **Simplicity**: Easy to implement and understand.
- **Efficiency**: Works well with large datasets and is computationally efficient in practice.

### **Cons:**

- **Number of Clusters**: The number of clusters \( k \) must be specified in advance, which can be challenging to determine.
- **Sensitivity to Initial Centroids**: Different initializations can lead to different results. This can sometimes be mitigated with techniques like K-means++ for better initial centroid selection.
- **Cluster Shape**: Assumes clusters are spherical and equally sized, which can be a limitation if the actual data doesn’t fit these assumptions.

### **Applications:**

K-means is used in various domains for tasks such as market segmentation, image compression, and pattern recognition.