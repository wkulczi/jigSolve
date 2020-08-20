import numpy as np
import cv2


def process():
    base = cv2.imread('images/cale_puzzle.png')
    img = cv2.imread('images/jeden_puzzel.png')

    sift = cv2.xfeatures2d.SIFT_create()

    kp1, des1 = sift.detectAndCompute(img, None)
    kp2, des2 = sift.detectAndCompute(base, None)

    flann = cv2.FlannBasedMatcher()
    matches = flann.knnMatch(des1, des2, k=2)

    good = []
    for m, n in matches:
        if m.distance < 0.7 * n.distance:
            good.append(m)

    src_pts = np.float32([kp1[m.queryIdx].pt for m in good]).reshape(-1, 1, 2)
    dst_pts = np.float32([kp2[m.queryIdx].pt for m in good]).reshape(-1, 1, 2)

    m, mask = cv2.findHomography(src_pts, dst_pts, cv2.RANSAC, 5.0)
    matches_mask = mask.ravel().tolist()

    d, h, w = img.shape[::-1]
    pts = np.float32([[0, 0], [0, h - 1], [w - 1, h - 1], [w - 1, 0]]).reshape(-1, 1, 2)
    # dst = cv2.perspectiveTransform(pts, m)

    # base = cv2.polylines(base, [np.int32(dst)], True, 255, 3, cv2.LINE_AA)

    img3 = cv2.drawMatches(img, kp1, base, kp2, good, None, matchColor=(0, 255, 255), singlePointColor=None,
                           matchesMask=matches_mask, flags=cv2.DRAW_MATCHES_FLAGS_DRAW_RICH_KEYPOINTS)

    cv2.imwrite('images/dopasowanie.png', img3)
