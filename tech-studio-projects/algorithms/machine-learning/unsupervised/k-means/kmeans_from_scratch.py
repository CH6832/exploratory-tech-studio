import numpy as np
import matplotlib.pyplot as plt

def initialize_centroids(X, k):
    """Randomly initialize k centroids from the data points."""
    np.random.seed(42)
    random_indices = np.random.choice(X.shape[0], k, replace=False)
    return X[random_indices]

def assign_clusters(X, centroids):
    """Assign each data point to the nearest centroid."""
    distances = np.linalg.norm(X[:, np.newaxis] - centroids, axis=2)
    return np.argmin(distances, axis=1)

def update_centroids(X, labels, k):
    """Update centroids to be the mean of the data points in each cluster."""
    return np.array([X[labels == i].mean(axis=0) for i in range(k)])

def kmeans(X, k, max_iters=100):
    """K-means clustering algorithm."""
    centroids = initialize_centroids(X, k)
    for _ in range(max_iters):
        labels = assign_clusters(X, centroids)
        new_centroids = update_centroids(X, labels, k)
        if np.all(centroids == new_centroids):
            break
        centroids = new_centroids
    return labels, centroids

# Generate synthetic data
np.random.seed(42)
X1 = np.random.randn(150, 2) + np.array([0, -5])
X2 = np.random.randn(150, 2) + np.array([5, 5])
X3 = np.random.randn(150, 2) + np.array([-5, 5])
X4 = np.random.randn(150, 2) + np.array([0, 5])
X = np.vstack([X1, X2, X3, X4])

# Apply K-means
k = 4
labels, centroids = kmeans(X, k)

# Plot the clusters and centroids
plt.scatter(X[:, 0], X[:, 1], c=labels, s=50, cmap='viridis')
plt.scatter(centroids[:, 0], centroids[:, 1], c='red', s=200, alpha=0.75, marker='X')
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.title('K-means Clustering from Scratch')
plt.show()
